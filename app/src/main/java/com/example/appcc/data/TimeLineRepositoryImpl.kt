package com.example.appcc.data

import android.util.Log
import com.example.appcc.model.ContentX
import com.example.appcc.model.UserModel
import com.example.appcc.utils.Const
import com.example.appcc.utils.Const.TIMELINE
import com.example.appcc.utils.UiState
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class TimeLineRepositoryImpl(val database: FirebaseDatabase) : TimeLineRepository {
    override fun getTimeLine(result: (UiState<MutableList<ContentX>>) -> Unit) {
        val user = Firebase.auth.currentUser
        val timeLine : MutableList<ContentX> = arrayListOf()
        if (user != null) {
            database.getReference(Const.USER).addChildEventListener(object : ChildEventListener {
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    val userModel: UserModel? = snapshot.getValue(UserModel::class.java)
                    for( i in snapshot.child(TIMELINE).children){
                        var contenx: ContentX? =i.getValue(ContentX::class.java)
                        if (contenx != null) {
                            timeLine.add(contenx)
                        }
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
    }
    override fun addTimeLine(contenx: ContentX, result: (UiState<String>) -> Unit) {
        val user = Firebase.auth.currentUser
        if (user != null) {
            val key: String? = database.getReference(Const.USER).push().key
            if (key != null) {
                database.getReference(Const.USER).child(user.uid).child(Const.TIMELINE).child(key)
                    .setValue(contenx)
                    .addOnSuccessListener {
                        result.invoke(UiState.Success("Thanh cong"))
                    }
                    .addOnFailureListener {
                        result.invoke(UiState.Failure(it.localizedMessage))
                    }
            }
        }
    }

    override fun updateTimeLine(contenx: ContentX, result: (UiState<String>) -> Unit) {

    }
}