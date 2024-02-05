/*
 * Copyright 2021 WangYiqian
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 *
 */

package com.example.stockchart.childchart.macdchart

import com.example.stockchart.DEFAULT_CHART_MAIN_DISPLAY_AREA_PADDING_BOTTOM
import com.example.stockchart.DEFAULT_CHART_MAIN_DISPLAY_AREA_PADDING_TOP
import com.example.stockchart.DEFAULT_CHILD_CHART_HEIGHT
import com.example.stockchart.DEFAULT_CHILD_CHART_MARGIN_BOTTOM
import com.example.stockchart.DEFAULT_CHILD_CHART_MARGIN_TOP
import com.example.stockchart.DEFAULT_MACD_BAR_SPACE_RATIO
import com.example.stockchart.DEFAULT_MACD_DEA_LINE_COLOR
import com.example.stockchart.DEFAULT_MACD_DEA_LINE_STROKE_WIDTH
import com.example.stockchart.DEFAULT_MACD_DIF_LINE_COLOR
import com.example.stockchart.DEFAULT_MACD_DIF_LINE_STROKE_WIDTH
import com.example.stockchart.DEFAULT_MACD_INDEX
import com.example.stockchart.DEFAULT_MACD_TEXT_COLOR
import com.example.stockchart.childchart.base.BaseChildChartConfig
import com.example.stockchart.childchart.base.HighlightLabelConfig
import com.example.stockchart.index.Index
import com.example.stockchart.listener.OnHighlightListener


/**
 * @author wangyiqian E-mail: wangyiqian9891@gmail.com
 * @version 创建时间: 2021/2/18
 */
class MacdChartConfig(
    height: Int = DEFAULT_CHILD_CHART_HEIGHT,
    marginTop: Int = DEFAULT_CHILD_CHART_MARGIN_TOP,
    marginBottom: Int = DEFAULT_CHILD_CHART_MARGIN_BOTTOM,
    onHighlightListener: OnHighlightListener? = null,
    chartMainDisplayAreaPaddingTop: Float = DEFAULT_CHART_MAIN_DISPLAY_AREA_PADDING_TOP,
    chartMainDisplayAreaPaddingBottom: Float = DEFAULT_CHART_MAIN_DISPLAY_AREA_PADDING_BOTTOM,
    // 长按时高亮线左侧标签配置
    var highlightLabelLeft: HighlightLabelConfig? = null,
    // 长按时高亮线右侧标签配置
    var highlightLabelRight: HighlightLabelConfig? = null,
    // dif线颜色
    var difLineColor: Int = DEFAULT_MACD_DIF_LINE_COLOR,
    // dif线宽度
    var difLineStrokeWidth: Float = DEFAULT_MACD_DIF_LINE_STROKE_WIDTH,
    // dea线颜色
    var deaLineColor: Int = DEFAULT_MACD_DEA_LINE_COLOR,
    // dea线宽度
    var deaLineStrokeWidth: Float = DEFAULT_MACD_DEA_LINE_STROKE_WIDTH,
    // macd文字颜色
    var macdTextColor: Int = DEFAULT_MACD_TEXT_COLOR,
    // 柱子之间的空间占比柱子宽度
    var barSpaceRatio: Float = DEFAULT_MACD_BAR_SPACE_RATIO,
    // 需要展示的指标配置
    var index: Index? = DEFAULT_MACD_INDEX
) : BaseChildChartConfig(
    height,
    marginTop,
    marginBottom,
    onHighlightListener,
    chartMainDisplayAreaPaddingTop,
    chartMainDisplayAreaPaddingBottom
)