package com.zzz.sexstatistic.presentation.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.zzz.sexstatistic.presentation.Routes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

sealed class NavMenuItem(var route: String, var icon: ImageVector, var title: String) {
	object Main : NavMenuItem(Routes.MAIN, Icons.Filled.Home, "Главный")
	object Inbox : NavMenuItem(Routes.INBOX, Icons.Filled.Inbox, "Входящие")
	object Partners : NavMenuItem(Routes.PARTNERS, Icons.Filled.Facebook, "Партнеры")
	object Profile : NavMenuItem(Routes.PROFILE, Icons.Filled.Person, "Профиль")
	object LogOut : NavMenuItem(Routes.SIGN_IN, Icons.Filled.ExitToApp, "Выйти")
}

@Composable
fun Menu(scope: CoroutineScope, scaffoldState: ScaffoldState, navController: NavController) {
	val items = listOf(
		NavMenuItem.Main,
		NavMenuItem.LogOut,
	)

	Column(
		modifier = Modifier
	) {
		Spacer(
			modifier = Modifier
				.fillMaxWidth()
				.height(5.dp)
		)
		val navBackStackEntry by navController.currentBackStackEntryAsState()
		val currentRoute = navBackStackEntry?.destination?.route
		items.forEach { item ->
			MenuItem(item = item, selected = currentRoute == item.route, onItemClick = {
				navController.navigate(item.route) {
					navController.graph.startDestinationRoute?.let { route ->
						popUpTo(route) {
							saveState = true
						}
					}
					if (item.route in listOf(Routes.SIGN_IN, Routes.SIGN_UP)) {
						navController.backQueue.clear()
					}
					restoreState = true
					launchSingleTop = true
				}
				scope.launch {
					scaffoldState.drawerState.close()
				}
			})
		}
		Spacer(modifier = Modifier.weight(1f))
		Text(
			text = "Developed by John Codeos",
			color = Color.White,
			textAlign = TextAlign.Center,
			fontWeight = FontWeight.Bold,
			modifier = Modifier
				.padding(12.dp)
				.align(Alignment.CenterHorizontally)
		)
	}
}

@Preview(showBackground = true)
@Composable
fun MenuPreview() {
	val scope = rememberCoroutineScope()
	val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
	val navController = rememberNavController()
	Menu(scope = scope, scaffoldState = scaffoldState, navController = navController)
}

@Composable
fun MenuItem(item: NavMenuItem, selected: Boolean, onItemClick: (NavMenuItem) -> Unit) {
	val background = if (selected) MaterialTheme.colors.primary else MaterialTheme.colors.secondary
	Row(
		verticalAlignment = Alignment.CenterVertically,
		modifier = Modifier
			.fillMaxWidth()
			.clickable(onClick = { onItemClick(item) })
			.height(45.dp)
			.background(background)
			.padding(start = 10.dp)
	) {
		Image(
			imageVector = item.icon,
			contentDescription = item.title,
			colorFilter = ColorFilter.tint(Color.White),
			contentScale = ContentScale.Fit,
			modifier = Modifier
				.height(35.dp)
				.width(35.dp)
		)
		Spacer(modifier = Modifier.width(7.dp))
		Text(
			text = item.title,
			fontSize = 18.sp,
			color = Color.White
		)
	}
}

@Preview(showBackground = false)
@Composable
fun MenuItemPreview() {
	MenuItem(item = NavMenuItem.Main, selected = false, onItemClick = {})
}