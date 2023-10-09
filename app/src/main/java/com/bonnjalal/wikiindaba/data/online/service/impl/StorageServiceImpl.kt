package com.bonnjalal.wikiindaba.data.online.service.impl;

import androidx.core.os.trace
import androidx.tracing.traceAsync
import com.bonnjalal.wikiindaba.data.online.online_entity.AttendeeOnlineEntity
import com.bonnjalal.wikiindaba.data.online.online_entity.OrganizerOnlineEntity
import com.bonnjalal.wikiindaba.data.online.online_entity.ProgramOnlineEntity
import com.bonnjalal.wikiindaba.data.online.service.AccountService
import com.bonnjalal.wikiindaba.data.online.service.StorageService
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.dataObjects
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
            auth.currentUser.flatMapLatest { user ->
                firestore.collection(TASK_COLLECTION).whereEqualTo(USER_ID_FIELD, user.id).dataObjects()
            }

    override suspend fun getAttendee(id: String): AttendeeOnlineEntity? =
        firestore.collection(TASK_COLLECTION).document(id).get().await().toObject(AttendeeOnlineEntity::class.java)


    override suspend fun saveAttendee(attendee: AttendeeOnlineEntity): String =
        traceAsync(SAVE_TASK_TRACE, cookie = 10, block = {
            val taskWithUserId = attendee.copy(id = auth.currentUserId)
            firestore.collection(TASK_COLLECTION).add(taskWithUserId).await().id
        })

    override suspend fun updateAttendee(task: AttendeeOnlineEntity): Unit =
        traceAsync(UPDATE_TASK_TRACE, cookie = 12) {
            firestore.collection(TASK_COLLECTION).document(task.id).set(task).await()
        }

    override suspend fun deleteAttendee(id: String) {
        firestore.collection(TASK_COLLECTION).document(id).delete().await()
    }

    override val programs: Flow<List<ProgramOnlineEntity>>
        get() =
            auth.currentUser.flatMapLatest { user ->
                firestore.collection(TASK_COLLECTION).whereEqualTo(USER_ID_FIELD, user.id).dataObjects()
            }

    override suspend fun getProgram(id: String): ProgramOnlineEntity? =
        firestore.collection(TASK_COLLECTION).document(id).get().await().toObject(ProgramOnlineEntity::class.java)


    override suspend fun saveProgram(program: ProgramOnlineEntity): String =
        traceAsync(SAVE_TASK_TRACE, cookie = 10, block = {
            val taskWithUserId = program.copy(id = auth.currentUserId)
            firestore.collection(TASK_COLLECTION).add(taskWithUserId).await().id
        })

    override suspend fun updateProgram(program: ProgramOnlineEntity): Unit =
        traceAsync(UPDATE_TASK_TRACE, cookie = 12) {
            firestore.collection(TASK_COLLECTION).document(task.id).set(task).await()
        }

    override suspend fun deleteProgram(id: String) {
        firestore.collection(TASK_COLLECTION).document(id).delete().await()
    }

    override val organizers: Flow<List<OrganizerOnlineEntity>>
        get() =
            auth.currentUser.flatMapLatest { user ->
                firestore.collection(TASK_COLLECTION).whereEqualTo(USER_ID_FIELD, user.id).dataObjects()
            }

    override suspend fun getOrganizer(id: String): OrganizerOnlineEntity? =
        firestore.collection(TASK_COLLECTION).document(id).get().await().toObject(OrganizerOnlineEntity::class.java)

    override suspend fun saveOrganizer(organizer: OrganizerOnlineEntity): String =
        traceAsync(SAVE_TASK_TRACE, cookie = 10, block = {
            val taskWithUserId = program.copy(id = auth.currentUserId)
            firestore.collection(TASK_COLLECTION).add(taskWithUserId).await().id
        })

    override suspend fun updateOrganizer(organizer: OrganizerOnlineEntity): Unit =
        traceAsync(UPDATE_TASK_TRACE, cookie = 12) {
            firestore.collection(TASK_COLLECTION).document(task.id).set(task).await()
        }

    override suspend fun deleteOrganizer(id: String) {
        firestore.collection(TASK_COLLECTION).document(id).delete().await()
    }

    companion object {
        private const val USER_ID_FIELD = "userId"
        private const val TASK_COLLECTION = "tasks"
        private const val SAVE_TASK_TRACE = "saveTask"
        private const val UPDATE_TASK_TRACE = "updateTask"
    }
}
