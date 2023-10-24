package com.bonnjalal.wikiindaba.presentation.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bonnjalal.wikiindaba.presentation.ui.MainViewModel
import com.slaviboy.composeunits.dh


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabsScreen(vm: MainViewModel = hiltViewModel()){
    val userStateAnonymous by vm.userState.collectAsStateWithLifecycle(initialValue = false)
    val tabList = if (userStateAnonymous) listOf("Program", "Organizers") else listOf("Program", "Organizers", "Attendees")

    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState { tabList.size }

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(selectedTabIndex = selectedTabIndex) {
            tabList.forEachIndexed { index, item ->
                Tab(selected = index == selectedTabIndex,
                    onClick = { /*TODO*/ },
                    text = {
                        Text(text = item)
                    }
                )
            }
        }

        HorizontalPager(state = pagerState, modifier = Modifier
            .fillMaxSize()
            .weight(1f)) {index ->

        }
    }

}

@Preview
@Composable
fun prevTabs(){
    TabsScreen()
}