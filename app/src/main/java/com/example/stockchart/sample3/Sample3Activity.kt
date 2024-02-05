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

package com.example.stockchart.sample3

import android.graphics.Color
import android.graphics.DashPathEffect
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.stockchart.R
import com.example.stockchart.DataMock
import com.example.stockchart.StockChartConfig
import com.example.stockchart.entities.IKEntity
import com.example.stockchart.entities.KEntity
import com.example.stockchart.sample3.activechart.ActiveChartConfig
import com.example.stockchart.sample3.activechart.ActiveChartFactory
import com.example.stockchart.sample3.activechart.OnActiveIndustryClickListener
import com.example.stockchart.sample3.data.ActiveInfo
import com.example.stockchart.stockchart.StockChart
import com.example.stockchart.util.DimensionUtil
import java.util.*

/**
 * @author wangyiqian E-mail: wangyiqian9891@gmail.com
 * @version 创建时间: 2021/5/14
 */
class Sample3Activity : AppCompatActivity() {

    /**
     * 固定数据点个数（一天的K线数据点数），如果服务器实际下发数据不够数量，则K线展示不会撑满
     * 如果不需要固定数据点数而是下发多少数据展示多少数据并撑满，这里改成-1即可
     */
    private var fixDataCount = 330

    lateinit var stock_chart: StockChart
    /**
     * 总配置
     */
    private val stockChartConfig = StockChartConfig()

    /**
     * 异动K线图的配置
     */
    private lateinit var activeChartConfig: ActiveChartConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample3)

        stock_chart = findViewById(R.id.stock_chart)

        initStockChart()

        // 模拟10秒轮询加载数据
        var fileIdx = 1
        Timer().schedule(object : TimerTask() {
            override fun run() {
                loadData(fileIdx)
                if (fileIdx == 4) {
                    fileIdx = 1
                } else {
                    fileIdx++
                }
            }
        }, 0, 10000)
    }

    private fun initStockChart() {

        activeChartConfig = ActiveChartConfig(
            height = DimensionUtil.dp2px(this, 200f),
            chartMainDisplayAreaPaddingTop = DimensionUtil.dp2px(this, 15f)
                .toFloat(),
            chartMainDisplayAreaPaddingBottom = DimensionUtil.dp2px(this, 15f)
                .toFloat(),
            mountainChartColor = Color.parseColor("#d2cdd3"),
            mountainChartStrokeWidth = DimensionUtil.dp2px(this, 1f).toFloat(),
            mountainChartLinearGradientColors =
            intArrayOf(Color.parseColor("#88d2cdd3"), Color.parseColor("#00d2cdd3")),
            preClosePriceLineColor = Color.parseColor("#9d9ca1"),
            preCloseLineStrokeWidth = 1f,
            preCloseLinePathEffect = DashPathEffect(
                floatArrayOf(
                    DimensionUtil.dp2px(this, 5f).toFloat(),
                    DimensionUtil.dp2px(this, 2f).toFloat()
                ), 0f
            ),
            timeTextColor = Color.parseColor("#7c7b80"),
            timeTextSize = DimensionUtil.sp2px(this, 10f).toFloat(),
            timeTextMarginH = DimensionUtil.dp2px(this, 3f).toFloat(),
            timeTextMarginV = DimensionUtil.dp2px(this, 8f).toFloat(),
            activeColorGreen = Color.parseColor("#01c39e"),
            activeColorRed = Color.parseColor("#e53248"),
            activeCircleRadius = DimensionUtil.dp2px(this, 2f).toFloat(),
            activeCircleStrokeWidth = DimensionUtil.dp2px(this, 1f).toFloat(),
            activeLineStrokeWidth = DimensionUtil.dp2px(this, 1f).toFloat(),
            activeIndustryTextSize = DimensionUtil.sp2px(this, 10f).toFloat(),
            activeIndustryTextColor = Color.parseColor("#ffffff"),
            activeIndustryTextPaddingH = DimensionUtil.dp2px(this, 5f).toFloat(),
            activeIndustryTextPaddingV = DimensionUtil.dp2px(this, 1f).toFloat(),
            onActiveIndustryClickListener = object : OnActiveIndustryClickListener {
                override fun onActiveIndustryClick(activeInfo: ActiveInfo) {
                    Toast.makeText(
                        this@Sample3Activity,
                        "点击了[${activeInfo.industry}]",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            // 固定了数据点的个数后 时间就应该要写死了 不从下发的数据中取
            fixTimeLeft = if (fixDataCount >= 0) "09:30" else null,
            fixTimeRight = if (fixDataCount >= 0) "15:30" else null
        )

        stockChartConfig.apply {
            stock_chart.setConfig(this)
            // 图的背景色
            backgroundColor = Color.parseColor("#FFFFFF")
            addChildCharts(ActiveChartFactory(stock_chart, activeChartConfig))
        }
    }

    private fun loadData(fileIdx: Int) {
        DataMock.loadActiveChartData(this, fileIdx) { activeResponse ->
            activeChartConfig.preClosePrice = activeResponse.preClosePrice
            var kEntities = mutableListOf<IKEntity>()
            kEntities.addAll(activeResponse.data)
            if (fixDataCount >= 0) { // 配置了固定数据点数
                if (fixDataCount < kEntities.size) { // 多余的数据删除
                    kEntities = kEntities.subList(0, fixDataCount)
                } else { // 不够的数据补点为空白点EmptyKEntity
                    for (i in 0..(fixDataCount - kEntities.size)) {
                        kEntities.add(KEntity.obtainEmptyKEntity())
                    }
                }
            }
            stockChartConfig.setKEntities(kEntities)
            stock_chart.notifyChanged()
        }
    }
}