package com.realnigma.testtask.repository

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.realnigma.testtask.dagger.DaggerEmployeeAPIComponent
import com.realnigma.testtask.network.EmployeeAPI
import com.realnigma.testtask.network.EmployeeResponse
import com.realnigma.testtask.network.RemoteResponse
import com.realnigma.testtask.room.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class EmployeeRepository(private val employeeDao: EmployeeDao) {

    @Inject
    lateinit var api : EmployeeAPI

    private val disposable = CompositeDisposable()

    var hasError : Boolean = false
    var error : String? = null

    init {
        DaggerEmployeeAPIComponent.create().inject(this)
    }
    val specialties: LiveData<List<Specialty>> = employeeDao.getAllSpecialties()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getSpecialtyById(specialtyId: Int) : LiveData<SpecialtyWithEmployees> {
    return employeeDao.getSpecialtyById(specialtyId)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getEmployeeById(employeeId: Int) : LiveData<EmployeeWithSpecialties> {
        return employeeDao.getEmployeeById(employeeId)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun loadDataFromNetwork() {
        Log.w("Employee", "Loading data...")
        disposable.add(api.getEmployeesWithSpecialities()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<EmployeeResponse>() {
                override fun onSuccess(response: EmployeeResponse) {
                    Log.w("Employee", response.toString())
                    for (remoteResponse : RemoteResponse? in response.response) {
                        val employee = Employee(
                            0,
                            remoteResponse!!.f_name,
                            remoteResponse.l_name,
                            remoteResponse.birthday,
                            remoteResponse.avatr_url
                        )
                        val specialties = remoteResponse.specialty
                        val employeeId : Long
                        employeeId = employeeDao.insertEmployee(employee)

                        for (specialty : Specialty in specialties) {
                            val employeeSpecialtyRef = EmployeeSpecialtyRef(employeeId.toInt(), specialty.specialty_id)
                            employeeDao.insertSpecialty(specialty)
                            employeeDao.insertRef(employeeSpecialtyRef)
                        }
                        Log.w("Employee", "inserting data: $employee $specialties")
                    }
                }

                override fun onError(e: Throwable) {
                    hasError = true
                    error = e.javaClass.toString()
                    Log.w("Employee", "Load error: ${error!!}")
            }
            })
        )

    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun clearDisposable(){
        disposable.clear()
    }

}