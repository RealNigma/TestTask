package com.realnigma.testtask

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.realnigma.testtask.dagger.DaggerEmployeeAPIComponent
import com.realnigma.testtask.network.EmployeeAPI
import com.realnigma.testtask.network.EmployeeResponse
import com.realnigma.testtask.network.RemoteResponse
import com.realnigma.testtask.room.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class EmployeeRepository(private val employeeDao: EmployeeDao) {

    @Inject
    lateinit var api : EmployeeAPI


    var hasError : Boolean = false
    var error : String? = null

    init {
        DaggerEmployeeAPIComponent.create().inject(this)
    }

    val employees: LiveData<List<Employee>> = employeeDao.getAllEmployees()
    val specialties: LiveData<List<Specialty>> = employeeDao.getAllSpecialties()
    lateinit var remoteResponse : RemoteResponse

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun loadDataFromNetwork() {
        Log.w("Employee", "Loading data...")
        employeeDao.deleteAll()
        api.getEmployeesWithSpecialities().enqueue(object : Callback<EmployeeResponse> {
            override fun onFailure(call: Call<EmployeeResponse>, t: Throwable) {
                hasError = true
                error = t.javaClass.toString()
                Log.w("Employee", "Load error: ${error!!}")
            }
            override fun onResponse(
                call: Call<EmployeeResponse>,
                response: Response<EmployeeResponse>
            ) {

                Log.w("Employee", response.body().toString())
                for (remoteResponse : RemoteResponse? in response.body()?.response!!) {
                    val employee = Employee(
                        0,
                        remoteResponse!!.f_name,
                        remoteResponse.l_name,
                        remoteResponse.birthday,
                        remoteResponse.avatr_url
                    )
                    val specialties = remoteResponse.specialty
                    //employeeDao.insertSpecialty(specialties)
                    employeeDao.insertEmployeeAndSpecialties(employee, specialties)
                    //employeeDao.insertEmployee(employee)
                    Log.w("Employee", "inserting data: $employee $specialties")
                }
            }
        })
    }

}