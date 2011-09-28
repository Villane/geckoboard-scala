/*
 * Copyright 2011 ZeroTurnaround OÜ
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.zeroturnaround.geckoboard

/* Geckoboard common structures */
case class TextValue(value: Long, text: String = "", prefix: Option[String] = None)
case class LabelValue(value: Long, label: String, colour: Option[String] = None)

/** Data types used in several widgets. Not strongly typed for ease of converting to JSON. */
object Geckoboard {
  /** Reverse DisplayType reverses colors in some charts/widgets */
  type DisplayType = String
  val Reverse: DisplayType = "reverse"
  val Standard: DisplayType = "standard"

  /** Controls visibility of some aspects of widgets, e.g. percentage in Funnel */
  type Visibility = String
  val Hide: Visibility = "hide"
  val Show: Visibility = "show"
}
import Geckoboard._

/* Geckoboard Custom Widgets */
object NumberAndSecondaryStat {
  def apply(primary: TextValue, secondary: Option[TextValue] = None, `type`: Option[DisplayType] = None): NumberAndSecondaryStat =
    apply(primary :: secondary.toList, `type`)
}
case class NumberAndSecondaryStat(item: List[TextValue], `type`: Option[DisplayType])

object RAGNumbers {
  def apply(red: TextValue, amber: TextValue, green: TextValue): RAGNumbers = apply(red :: amber :: green :: Nil)
}
case class RAGNumbers(item: List[TextValue])

case class Text(item: List[TextItem])

object TextItem {
  type Type = Int
  val Normal = 0
  val Alert = 1
  val Info = 2
}
case class TextItem(text: String, `type`: TextItem.Type)

/* Map Widget */
object Mapping {
  def apply(points: List[MapPoint]): Mapping = apply(MapPoints(points))
}
case class Mapping(points: MapPoints)
case class MapPoints(point: List[MapPoint])
abstract class MapPoint {
  def size: Option[Int]
  def color: Option[String]
  def cssclass: Option[String]
}
case class CityPoint(city: City, size: Option[Int], color: Option[String], cssclass: Option[String]) extends MapPoint
case class City(city_name: String, region_code: Option[String], country_code: String)
case class GeoPoint(latitude: Double, longitude: Double, size: Option[Int], color: Option[String], cssclass: Option[String]) extends MapPoint
case class HostPoint(host: String, size: Option[Int], color: Option[String], cssclass: Option[String]) extends MapPoint
case class IpPoint(ip: String, size: Option[Int], color: Option[String], cssclass: Option[String]) extends MapPoint

/* Geckoboard Custom Charts */
case class PieChart(item: List[LabelValue])

case class LineChart(item: List[Long], settings: LineChartSettings)
case class LineChartSettings(axisx: List[String], axisy: List[String], colour: Option[String] = None)

case class GeckOMeter(item: Long, `type`: Option[DisplayType], min: TextValue, max: TextValue)

case class Funnel(item: List[LabelValue], `type`: Option[DisplayType] = None, percentage: Option[Visibility] = None)
