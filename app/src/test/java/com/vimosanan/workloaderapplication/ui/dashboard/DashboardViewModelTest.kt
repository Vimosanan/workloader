package com.vimosanan.workloaderapplication.ui.dashboard

import android.content.SharedPreferences
import com.vimosanan.workloaderapplication.network.ApiInterface
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class DashboardViewModelTest {

    private lateinit var dashboardViewModel: DashboardViewModel

    @Mock
    private lateinit var api : ApiInterface

    @Mock
    private lateinit var sharedPreferences: SharedPreferences

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        dashboardViewModel = DashboardViewModel(api, sharedPreferences)
    }

    @Test
    fun `should return date HHMMam format when submit date str`(){
        val input = "2020-02-25T09:59:18.956153Z"
        val expectedValue = "06:15pm"

        val actualValue = dashboardViewModel.getDisplayDateString(input)
        assertEquals(expectedValue, actualValue)
    }


    @After
    fun tearDown() {
    }


}