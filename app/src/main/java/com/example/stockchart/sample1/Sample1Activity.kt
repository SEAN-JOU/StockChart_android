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

package com.example.stockchart.sample1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.stockchart.R
import com.example.stockchart.DataMock
import com.example.stockchart.StockChartConfig
import com.example.stockchart.childchart.kchart.KChartConfig
import com.example.stockchart.childchart.kchart.KChartFactory
import com.example.stockchart.childchart.timebar.TimeBarConfig
import com.example.stockchart.childchart.timebar.TimeBarFactory
import com.example.stockchart.entities.IKEntity
import com.example.stockchart.stockchart.StockChart

/**
 * @author wangyiqian E-mail: wangyiqian9891@gmail.com
 * @version 创建时间: 2021/2/26
 */
class Sample1Activity : AppCompatActivity() {

    lateinit var stock_chart: StockChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample1)

        stock_chart = findViewById(R.id.stock_chart)
        // 总配置
        val stockChartConfig = StockChartConfig()
        stock_chart.setConfig(stockChartConfig)

        // K线图的配置与工厂
        val kChartConfig = KChartConfig()
        val kChartFactory = KChartFactory(stockChart = stock_chart, childChartConfig = kChartConfig)

        // 时间条图的配置与工厂
        val timeBarConfig = TimeBarConfig()
        val timeBarFactory =
            TimeBarFactory(stockChart = stock_chart, childChartConfig = timeBarConfig)

        // 将需要显示的子图的工厂加入全局配置
//        addChildCharts(kChartFactory, timeBarFactory)

        // 加载模拟数据
        DataMock.loadDayData(this, 0) { kEntities: List<IKEntity> ->

            // 初始显示最后50条数据
            val pageSize = 50

            // 设置加载到的数据
            stockChartConfig.setKEntities(
                kEntities,
                showStartIndex = kEntities.size - pageSize,
                showEndIndex = kEntities.size - 1
            )

            // 通知更新K线图
            stock_chart.notifyChanged()
        }

    }
}