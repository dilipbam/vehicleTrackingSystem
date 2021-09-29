package com.dilip.deliverytruckbooking_dilipbam

import com.dilip.deliverytruckbooking_dilipbam.API.MyServiceBuilder
import com.dilip.deliverytruckbooking_dilipbam.entity.Customer
import com.dilip.deliverytruckbooking_dilipbam.model.AddBooking
import com.dilip.deliverytruckbooking_dilipbam.model.CustomerUsername
import com.dilip.deliverytruckbooking_dilipbam.model.UpdateDetails
import com.dilip.deliverytruckbooking_dilipbam.repository.BookingRepository
import com.dilip.deliverytruckbooking_dilipbam.repository.CustomerRepository
import com.dilip.deliverytruckbooking_dilipbam.repository.OwnerRepo
import com.dilip.deliverytruckbooking_dilipbam.repository.OwnerRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class unitTesting {
    private lateinit var bookingRepository: BookingRepository
    private lateinit var CustomerRepository: CustomerRepository
    private lateinit var OwnerRepository: OwnerRepo
    var CustomerName = MyServiceBuilder.customerName.toString()
    var username = MyServiceBuilder.customerUsername.toString()

    @Test
    fun login() = runBlocking {
        try {
            CustomerRepository = CustomerRepository()
            val response = CustomerRepository.login("adarsha", "adarsha")
            val expectedResult = "login successfull"
            val actualResult = response.message
            Assert.assertEquals(expectedResult, actualResult)
        } catch (ex: Exception) {

        }

    }


    @Test
    fun registerCustomer() = runBlocking {
        try {
            var customer = Customer(
                firstName = "Ramesh",
                middleName = "Kumar",
                lastName = "Sharma",
                contact = "9841201124",
                email = "ramesh1@gmail.com",
                userName = "ramesh1",
                password = "ramesh123"
            )
            CustomerRepository = CustomerRepository()
            val response = CustomerRepository.registerCustomer(customer)
            val expectedResult = "Successfully Registered"
            val actualResult = response.message
            Assert.assertEquals(expectedResult, actualResult)
        } catch (ex: java.lang.Exception) {
        }

    }

    @Test
    fun updateCustomer() = runBlocking {
        try {

            var update = UpdateDetails(
                username = MyServiceBuilder.ownerUserName,
                userEmail = "Shyam@gmail.com",
                userPhone = "9876655443"
            )
            OwnerRepository = OwnerRepo()
            val response = OwnerRepository.updateOwner(update)
            val expectedResult = "updated"
            val actualResult = response.message
            Assert.assertEquals(expectedResult, actualResult)
        } catch (ex: java.lang.Exception) {
        }
    }

    @Test
    fun addBooking() = runBlocking {
        try {
            var addBooking = AddBooking(
                Username = username,
                VehicleName = "s",
                VehicleNumber = "12345",
                DrivingLicense = "21-90-32",
                DrivingName = "dilip",
                Capacity = "15",
                Rate = 1234,
                Radio = "asfsdsaf",
                PickLocation = "asdfsadfsdfasf",
                DeliverLocation = "sdfsadfsdfsadf",
                Phone = "123456789",
                Date = "12/4/56"

            )
            bookingRepository = BookingRepository()
            val response = bookingRepository.booking(addBooking)
            val expectedResult = "success"
            val actualResult = response.message
            Assert.assertEquals(expectedResult, actualResult)
        } catch (ex: java.lang.Exception) {
        }
    }

    @Test
    fun getBooking() = runBlocking {
        try {


            CustomerRepository = CustomerRepository()
            val response = CustomerRepository.getBooking("adarsha")
            val expectedResult = "fetched data"
            val actualResult = response.message
            Assert.assertEquals(expectedResult, actualResult)
        } catch (ex: java.lang.Exception) {
        }
    }
    @Test
    fun deleteFromHistory() = runBlocking {

        val customerUsername = CustomerUsername(username)
        try {
            CustomerRepository = CustomerRepository()
            val response = CustomerRepository.deleteFromHistory(customerUsername)
            val expectedResult = ""
            val actualResult = response.message
            Assert.assertEquals(expectedResult, actualResult)

        }catch (ex: java.lang.Exception){

        }
    }

}

