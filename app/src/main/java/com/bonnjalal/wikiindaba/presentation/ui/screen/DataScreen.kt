package com.bonnjalal.wikiindaba.presentation.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bonnjalal.wikiindaba.R
import com.bonnjalal.wikiindaba.presentation.ui.MainViewModel
import com.slaviboy.composeunits.dh
import com.slaviboy.composeunits.dw


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabsScreen(navigate: (String) -> Unit,logout:(String) -> Unit, vm: MainViewModel){
    val userStateAnonymous by vm.userState.collectAsStateWithLifecycle(initialValue = false)
    val tabList = if (userStateAnonymous) listOf("Program", "Organizers") else listOf("Program", "Organizers", "Attendees")

    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState { tabList.size }

    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0xFFA39274))) {
        val (logo, col1) = createRefs()
        Row (modifier = Modifier
            .constrainAs(logo) {
                height = Dimension.wrapContent
                width = Dimension.fillToConstraints
                top.linkTo(parent.top, margin = 0.008.dh)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }){

            Spacer(modifier = Modifier.fillMaxWidth(0.1f))
            Image(modifier = Modifier
                .width(0.25.dw)
                .height(0.035.dh)
                .align(Alignment.CenterVertically)
//            .size(width = 160.dp, height = 60.dp)
                , imageVector = ImageVector.vectorResource(id = R.drawable.logo_wikiindaba),
                contentDescription = "indaba logo", contentScale = ContentScale.FillBounds)

            Spacer(modifier = Modifier.fillMaxWidth(0.7f))
            IconButton(onClick = { vm.onSignOut(logout) }, modifier = Modifier.align(Alignment.CenterVertically)) {
                Icon(painter = painterResource(id = R.drawable.material_symbols_logout),
                    contentDescription = "logout logo", tint = Color(0xFFF5EEDF)
                )
            }
        }

        LaunchedEffect(key1 = selectedTabIndex){
            pagerState.animateScrollToPage(selectedTabIndex)
        }
        LaunchedEffect(key1 = pagerState.currentPage, key2 = pagerState.isScrollInProgress){
            if(!pagerState.isScrollInProgress) selectedTabIndex = pagerState.currentPage
        }
        Column(modifier = Modifier
            .background(
                color = Color.White,
                shape = RoundedCornerShape(topStart = 0.035.dh, topEnd = 0.035.dh)
            )
            .constrainAs(col1) {
                top.linkTo(logo.bottom, margin = 0.015.dh)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                height = Dimension.fillToConstraints
                width = Dimension.fillToConstraints
            }) {
            TabRow(modifier = Modifier.background(
                color = Color.Transparent,
                shape = RoundedCornerShape(topStart = 0.035.dh, topEnd = 0.035.dh)),
                containerColor = Color.Transparent,
                selectedTabIndex = selectedTabIndex) {
                tabList.forEachIndexed { index, item ->
                    Tab(selected = index == selectedTabIndex,
                        onClick = { selectedTabIndex = index},
                        text = {
                            Text(text = item)
                        }
                    )
                }
            }

            HorizontalPager(state = pagerState, modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.85f)
                .align(Alignment.CenterHorizontally)
                .weight(1f)
            ) {index ->
                when (index) {
                    0 -> {
                        ProgramScreen(modifier = Modifier.fillMaxSize(), navigate = navigate, vm = vm)
                    }
                    1 -> {
                        OrganizerScreen(modifier = Modifier.fillMaxSize(),vm = vm)
                    }
                    2 -> {
                        AttendeeScreen(modifier = Modifier.fillMaxSize(),vm = vm)
                    }
                }
            }
        }
    }

}

@Preview
@Composable
fun prevTabs(){
//    TabsScreen()
}