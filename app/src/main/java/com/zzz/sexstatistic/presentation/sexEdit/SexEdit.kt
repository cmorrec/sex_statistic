package com.zzz.sexstatistic.presentation.sexEdit

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.zzz.sexstatistic.presentation.ActionStatus

@Composable
fun SexEdit(
    sexId: String?,
    navHostController: NavHostController,
    sexViewModel : SexViewModel = hiltViewModel(),
) {
    val currentSex = sexViewModel.currentSex.collectAsState()
    val getSexStatus = sexViewModel.getSexStatus.collectAsState()
    val saveSexStatus = sexViewModel.saveSexStatus.collectAsState()
    if (currentSex.value == null && getSexStatus.value == ActionStatus.INIT) {
        sexViewModel.getSexById(sexId)
    }
    val isLoading = getSexStatus.value == ActionStatus.LOADING || saveSexStatus.value == ActionStatus.LOADING
    Box {
        if (isLoading) CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        Button(onClick = { sexViewModel.saveSex(currentSex.value!!, navHostController) }) {
            Text("Save")
        }
    }
}

@Preview(showBackground = false)
@Composable
fun SexEditPreview() {
    val navHostController = rememberNavController()
	SexEdit(sexId = "1", navHostController = navHostController)
}
