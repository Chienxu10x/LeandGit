package com.example.appcc.data

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import com.example.appcc.model.CommentModel
import com.example.appcc.model.ContentX
import com.example.appcc.model.UserModel
import com.example.appcc.utils.Const
import com.example.appcc.utils.UiState
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import kotlin.math.log

class TimeLineRepositoryImpl(val database: FirebaseDatabase, val storage: FirebaseStorage) :
    TimeLineRepository {
    @SuppressLint("SuspiciousIndentation")
    override fun getTimeLine(result: (UiState<MutableList<CommentModel>>) -> Unit) {
        val timeLine: MutableList<CommentModel> = arrayListOf()
        database.getReference(Const.COMMENT).addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val comment: CommentModel? = snapshot.getValue(CommentModel::class.java)
                if (comment != null) {
                    timeLine.add(comment)
                }
                result.invoke(
                    UiState.Success(timeLine)
                )
                Log.d("TAG", "onChildAdded: " + timeLine.size)
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildRemoved(snapshot: DataSnapshot) {

            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }

    override fun addTimeLine(commentModel: CommentModel,id:String, result: (UiState<String>) -> Unit) {
        val user = Firebase.auth.currentUser
        if (user != null) {
//            val key: String? = database.getReference(Const.COMMENT).push().key

                database.getReference(Const.COMMENT).child(id)
                    .setValue(commentModel)
                    .addOnSuccessListener {
                        result.invoke(UiState.Success("Thanh cong"))
                    }
                    .addOnFailureListener {
                        result.invoke(UiState.Failure(it.localizedMessage))
                    }

        }
    }

    override fun updateTimeLine(contenx: ContentX, result: (UiState<String>) -> Unit) {

    }

    override fun getMyimeLine(result: (UiState<MutableList<CommentModel>>) -> Unit) {
        val user = Firebase.auth.currentUser
        val timeLine: MutableList<CommentModel> = arrayListOf()
        if (user != null) {
            database.getReference(Const.COMMENT).addChildEventListener(object : ChildEventListener {
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    val comment: CommentModel? = snapshot.getValue(CommentModel::class.java)
                    if (comment != null && comment.idUser.equals(user.uid)) {
                        timeLine.add(comment)
                    }
                    result.invoke(
                        UiState.Success(timeLine)
                    )
                    Log.d("TAG", "onChildAdded: " + timeLine.size)
                }

                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

                }

                override fun onChildRemoved(snapshot: DataSnapshot) {

                }

                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
        }
    }

    override fun getFavorite(result: (UiState<MutableList<ContentX>>) -> Unit) {
        val user = Firebase.auth.currentUser
        val favoriteTheme: MutableList<ContentX> = arrayListOf()
        if (user == null) {
//            Toast.makeText(this,"aaa",Toast.LENGTH_SHORT).show()
            return
        }
        database.getReference(Const.USER).child(user.uid).child(Const.FAVORITE)
            .addChildEventListener(object : ChildEventListener {
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    val contenx: ContentX? = snapshot.getValue(ContentX::class.java)
                    if (contenx != null) {
                        favoriteTheme.add(contenx)
                    }
                    result.invoke(UiState.Success(favoriteTheme))
                }

                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

                }

                override fun onChildRemoved(snapshot: DataSnapshot) {

                }

                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
    }

    override fun getUserModel(result: (UiState<MutableList<UserModel>>) -> Unit) {
        val user = Firebase.auth.currentUser
        val timeLine: MutableList<UserModel> = arrayListOf()
        if (user == null) {
            return
        }

        database.getReference(Const.USER).addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val userModel = snapshot.getValue(UserModel::class.java)
                if (userModel != null && userModel.id.equals(user.uid)) {
                    timeLine.add(userModel)

                }
                result.invoke(UiState.Success(timeLine))



            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildRemoved(snapshot: DataSnapshot) {

            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    override fun addFavorite(
        contenx: ContentX,
        titleTheme: String,
        result: (UiState<String>) -> Unit
    ) {
        val user = Firebase.auth.currentUser
        if (user != null) {
            Log.d("TAG", "addFavorite: " + user.uid)
            database.getReference(Const.USER).child(user.uid).child(Const.FAVORITE)
                .child(titleTheme)
                .setValue(contenx)
                .addOnSuccessListener {
                    result.invoke(UiState.Success("Thanh cong"))
                }
                .addOnFailureListener {
                    result.invoke(UiState.Failure(it.localizedMessage))
                }
        }
    }

    override fun updateProfile(uri: Uri, result: (UiState<String>) -> Unit) {
        val user = Firebase.auth.currentUser
        if (user == null) {
            return
        }
        val imageRef: StorageReference =
            storage.getReference().child(Const.AVATAR + "/" + uri.lastPathSegment)
        val uploadTask: UploadTask = imageRef.putFile(uri)
        uploadTask.addOnSuccessListener { taskSnapshot ->
            val uriTask = imageRef.downloadUrl
            uriTask.addOnSuccessListener { uri ->
                database.getReference(Const.USER).child(user.uid).child(Const.AVATAR)
                    .setValue(uri.toString(), object : DatabaseReference.CompletionListener {
                        override fun onComplete(
                            error: DatabaseError?,
                            ref: DatabaseReference
                        ) {
                            database.getReference(Const.COMMENT)
                                .addListenerForSingleValueEvent(object : ValueEventListener {
                                    override fun onDataChange(snapshot: DataSnapshot) {
                                        val updatedComments = mutableListOf<CommentModel>()

                                        for (commentSnapshot in snapshot.children) {
                                            val comment: CommentModel? = commentSnapshot.getValue(CommentModel::class.java)
                                            if (comment != null && comment.idUser == user.uid) {
                                                // Cập nhật trường avatar cho comment
                                                comment.avatar = uri.toString()
                                                updatedComments.add(comment)
                                            }
                                        }

                                        // Lặp qua danh sách các comment cần cập nhật và thực hiện cập nhật trong cơ sở dữ liệu
                                        for (updatedComment in updatedComments) {
                                            val commentId = updatedComment.id
                                            database.getReference(Const.COMMENT).child(commentId).setValue(updatedComment)
                                        }
                                    }

                                    override fun onCancelled(error: DatabaseError) {
                                        // Xử lý khi có lỗi xảy ra
                                    }
                                })

                            result.invoke(UiState.Success("Success"))
                        }
                    })
            }
        }
    }
}