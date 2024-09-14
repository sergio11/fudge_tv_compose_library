package com.dreamsoftware.fudge.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.TabDefaults
import androidx.tv.material3.TabRow
import androidx.tv.material3.Tab
import androidx.tv.material3.TabRowDefaults
import androidx.tv.material3.TabRowScope

@Composable
@OptIn(ExperimentalComposeUiApi::class)
fun FudgeTvTabRow(
    modifier: Modifier = Modifier,
    @StringRes tabsRes: List<Int> = emptyList(),
    tabs: List<String> = emptyList(),
    selectedTabIndex: Int,
    focusTabIndex: Int,
    enableRowIndicator: Boolean = false,
    tabContentColor: Color? = null,
    tabSelectedContentColor: Color? = null,
    tabInactiveContentColor: Color? = null,
    indicatorActiveColor: Color? = null,
    indicatorInverseActiveColor: Color? = null,
    indicatorInactiveColor: Color? = null,
    onClick: (Int) -> Unit,
    onFocus: (Int) -> Unit,
) {
    with(MaterialTheme.colorScheme) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = modifier.focusRestorer(),
            separator = { Spacer(modifier = Modifier.width(16.dp)) },
            indicator = { tabPositions, doesTabRowHaveFocus ->
                if (enableRowIndicator) {
                    FudgeTvTabRowIndicator(
                        currentTabPosition = tabPositions[selectedTabIndex],
                        doesTabRowHaveFocus = doesTabRowHaveFocus,
                        activeColor = indicatorActiveColor ?: secondary,
                    )
                } else {
                    TabRowDefaults.PillIndicator(
                        currentTabPosition = tabPositions[focusTabIndex],
                        activeColor = indicatorActiveColor ?: secondary,
                        inactiveColor = indicatorInactiveColor ?: Color.Transparent,
                        doesTabRowHaveFocus = doesTabRowHaveFocus,
                    )
                    TabRowDefaults.PillIndicator(
                        currentTabPosition = tabPositions[selectedTabIndex],
                        activeColor = indicatorInverseActiveColor ?: primary,
                        doesTabRowHaveFocus = doesTabRowHaveFocus,
                    )
                }
            },
        ) {
            RenderTabsOrRes(
                tabs = tabs,
                tabsRes = tabsRes,
                selectedTabIndex = selectedTabIndex,
                tabContentColor = tabContentColor,
                tabSelectedContentColor = tabSelectedContentColor,
                tabInactiveContentColor = tabInactiveContentColor,
                onClick = onClick,
                onFocus = onFocus
            )
        }
    }
}

@Composable
private fun TabRowScope.RenderTabsOrRes(
    tabs: List<String>,
    @StringRes tabsRes: List<Int>,
    selectedTabIndex: Int,
    tabContentColor: Color? = null,
    tabSelectedContentColor: Color? = null,
    tabInactiveContentColor: Color? = null,
    onClick: (Int) -> Unit,
    onFocus: (Int) -> Unit
) {
    if (tabs.isNotEmpty()) {
        tabs.forEachIndexed { index, tab ->
            TabItem(
                index = index,
                titleText = tab,
                selectedTabIndex = selectedTabIndex,
                tabContentColor = tabContentColor,
                tabSelectedContentColor = tabSelectedContentColor,
                tabInactiveContentColor = tabInactiveContentColor,
                onClick = onClick,
                onFocus = onFocus
            )
        }
    } else if (tabsRes.isNotEmpty()) {
        tabsRes.forEachIndexed { index, tabRes ->
            TabItem(
                index = index,
                titleRes = tabRes,
                selectedTabIndex = selectedTabIndex,
                tabContentColor = tabContentColor,
                tabSelectedContentColor = tabSelectedContentColor,
                tabInactiveContentColor = tabInactiveContentColor,
                onClick = onClick,
                onFocus = onFocus
            )
        }
    }
}

@Composable
private fun TabRowScope.TabItem(
    index: Int,
    titleText: String? = null,
    @StringRes titleRes: Int? = null,
    selectedTabIndex: Int,
    tabContentColor: Color? = null,
    tabSelectedContentColor: Color? = null,
    tabInactiveContentColor: Color? = null,
    onClick: (Int) -> Unit,
    onFocus: (Int) -> Unit
) {
    with(MaterialTheme.colorScheme) {
        key(index) {
            Tab(
                selected = index == selectedTabIndex,
                onClick = { onClick(index) },
                onFocus = { onFocus(index) },
                colors = TabDefaults.underlinedIndicatorTabColors(
                    contentColor = tabContentColor ?: secondary,
                    selectedContentColor = tabSelectedContentColor ?: secondary,
                    inactiveContentColor = tabInactiveContentColor ?: onSurface,
                )
            ) {
                FudgeTvText(
                    type = FudgeTvTextTypeEnum.LABEL_LARGE,
                    titleText = titleText,
                    titleRes = titleRes,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                    textColor = tabContentColor
                )
            }
        }
    }
}

