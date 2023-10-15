package com.bonnjalal.wikiindaba.data.online.service.impl;

import androidx.tracing.traceAsync
import com.bonnjalal.wikiindaba.data.online.online_entity.AttendanceOnlineEntity
import com.bonnjalal.wikiindaba.data.online.online_entity.AttendeeOnlineEntity
import com.bonnjalal.wikiindaba.data.online.online_entity.OrganizerOnlineEntity
import com.bonnjalal.wikiindaba.data.online.online_entity.ProgramOnlineEntity
import com.bonnjalal.wikiindaba.data.online.service.AccountService
import com.bonnjalal.wikiindaba.data.online.service.StorageService
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.dataObjects
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StorageServiceImpl
@Inject
constructor(private val firestore: FirebaseFirestore, private val auth: AccountService) :
    StorageService {

    override val attendees: Flow<List<AttendeeOnlineEntity>>
        get() =
            firestore.collection(ATTENDEE_COLLECTION).dataObjects()
//            auth.currentUser.flatMapLatest { user ->
//                firestore.collection(ATTENDEE_COLLECTION).whereEqualTo(USER_ID_FIELD, user.id).dataObjects()
//            }

    override suspend fun getAttendee(id: String): AttendeeOnlineEntity? =
        firestore.collection(ATTENDEE_COLLECTION).document(id).get().await().toObject<AttendeeOnlineEntity?>()//?.copy(id = id)

    override suspend fun saveAttendee(attendee: AttendeeOnlineEntity): String {
        TODO("Not yet implemented")
    }


    /* override suspend fun saveAttendee(attendee: AttendeeOnlineEntity): String =
         traceAsync(SAVE_ATTENDEE_TRACE, cookie = 10, block = {
             // we don't add users from here, you should use google sheet or firebase console
             val taskWithUserId = attendee.copy(id = "attendeeID_" + (collectionLenth + 1))
             firestore.collection(ATTENDEE_COLLECTION).add(taskWithUserId).await().id
         })*/

    override suspend fun updateAttendee(attendee: AttendeeOnlineEntity): Unit =
        traceAsync(UPDATE_ATTENDEE_TRACE, cookie = 11) {
            firestore.collection(ATTENDEE_COLLECTION).document(attendee.id).set(attendee).await()
        }

    override suspend fun deleteAttendee(id: String) {
        firestore.collection(ATTENDEE_COLLECTION).document(id).delete().await()
    }

    override val programs: Flow<List<ProgramOnlineEntity>>
        get() =
            firestore.collection(PROGRAM_COLLECTION).dataObjects()

//            auth.currentUser.flatMapLatest { user ->
//                firestore.collection(PROGRAM_COLLECTION).whereEqualTo(USER_ID_FIELD, user.id).dataObjects()
//            }

    override suspend fun getProgram(id: String): ProgramOnlineEntity? =
        firestore.collection(PROGRAM_COLLECTION).document(id).get().await().toObject()//?.copy(id = id)

    override suspend fun saveProgram(program: ProgramOnlineEntity): String {
        TODO("Not yet implemented")
    }


    /*override suspend fun saveProgram(program: ProgramOnlineEntity): String =
        traceAsync(SAVE_PROGRAM_TRACE, cookie = 20, block = {
            // we don't add users from here, you should use google sheet or firebase console
            val taskWithUserId = program.copy(id = "programID_" + (collectionLenth + 1))
            firestore.collection(PROGRAM_COLLECTION).add(taskWithUserId).await().id
        })*/

    override suspend fun updateProgram(program: ProgramOnlineEntity): Unit =
        traceAsync(UPDATE_PROGRAM_TRACE, cookie = 21) {
            firestore.collection(PROGRAM_COLLECTION).document(program.id).set(program).await()
        }

    override suspend fun deleteProgram(id: String) {
        firestore.collection(PROGRAM_COLLECTION).document(id).delete().await()
    }

    override val organizers: Flow<List<OrganizerOnlineEntity>>
        get() =
            firestore.collection(ATTENDEE_COLLECTION).whereEqualTo(ROLE_FIELD, ROLE_VALUE).dataObjects()
//            auth.currentUser.flatMapLatest { user ->
//                firestore.collection(ATTENDEE_COLLECTION).whereEqualTo(USER_ID_FIELD, user.id).dataObjects()
//            }

    override suspend fun getOrganizer(id: String): OrganizerOnlineEntity? =
        firestore.collection(ATTENDEE_COLLECTION).document(id).get().await().toObject(OrganizerOnlineEntity::class.java)

    override suspend fun saveOrganizer(organizer: OrganizerOnlineEntity): String {
        TODO("Not yet implemented")
    }

    /*override suspend fun saveOrganizer(organizer: OrganizerOnlineEntity): String =
        traceAsync(SAVE_ATTENDEE_TRACE, cookie = 30, block = {
            // we don't add users from here, you should use google sheet or firebase console
            val taskWithUserId = organizer.copy(id = "attendeeID_" + (collectionLenth + 1))
            firestore.collection(ATTENDEE_COLLECTION).add(taskWithUserId).await().id
        })*/

    override suspend fun updateOrganizer(organizer: OrganizerOnlineEntity): Unit =
        traceAsync(UPDATE_ATTENDEE_TRACE, cookie = 31) {
            firestore.collection(ATTENDEE_COLLECTION).document(organizer.id).set(organizer).await()
        }

    override suspend fun deleteOrganizer(id: String) {
        firestore.collection(ATTENDEE_COLLECTION).document(id).delete().await()
    }


    override suspend fun getAttendance(path: String): Flow<List<AttendanceOnlineEntity>> =
        firestore.collection("$PROGRAM_COLLECTION/$path").dataObjects()

    override suspend fun saveAttendance(path:String, attendance: AttendanceOnlineEntity): String =
        traceAsync(SAVE_ATTENDANCE_TRACE, cookie = 40, block = {
            firestore.collection("$PROGRAM_COLLECTION/$path").add(attendance).await().id
        })

    override suspend fun updateAttendance(path:String, attendance: AttendanceOnlineEntity) {
        traceAsync(UPDATE_ATTENDANCE_TRACE, cookie = 41) {
            firestore.collection("$PROGRAM_COLLECTION/$path").document(attendance.name).set(attendance).await()
        }
    }

    override suspend fun deleteAttendance(path: String, name:String) {
        firestore.collection("$PROGRAM_COLLECTION/$path").document(name).delete().await()
    }

    companion object {
        private const val USER_ID_FIELD = "userId"

        private const val ATTENDEE_COLLECTION = "attendees"
        private const val SAVE_ATTENDEE_TRACE = "saveAttendee"
        private const val UPDATE_ATTENDEE_TRACE = "updateAttendee"

        private const val PROGRAM_COLLECTION = "programs"
        private const val SAVE_PROGRAM_TRACE = "saveProgram"
        private const val UPDATE_PROGRAM_TRACE = "updateProgram"

        private const val SAVE_ATTENDANCE_TRACE = "saveAttendance"
        private const val UPDATE_ATTENDANCE_TRACE = "updateAttendance"
        // For Organizers
        private const val ROLE_FIELD = "role"
        private const val ROLE_VALUE = "Core Team organizer"

    }
}
