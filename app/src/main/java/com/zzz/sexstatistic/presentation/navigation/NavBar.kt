package com.zzz.sexstatistic.presentation.navigation

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun NavBar(
	scope: CoroutineScope, scaffoldState: ScaffoldState
) {
	TopAppBar(
		title = { Text("Title of application") },
		navigationIcon = {
			IconButton(onClick = {
				scope.launch {
					scaffoldState.drawerState.open()
				}
			}) {
				Icon(Icons.Filled.Person, contentDescription = null)
			}
		},
	)
}

@Preview(showBackground = false)
@Composable
fun TopBarPreview() {
	val scope = rememberCoroutineScope()
	val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
	NavBar(scope = scope, scaffoldState = scaffoldState)
}
