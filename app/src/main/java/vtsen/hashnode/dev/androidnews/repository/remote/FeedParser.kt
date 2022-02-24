package vtsen.hashnode.dev.androidnews.repository.remote

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory

private const val TAG = "FeedParser"

class FeedParser {

    private val pullParserFactory = XmlPullParserFactory.newInstance()
    private val parser = pullParserFactory.newPullParser()

    fun parse(xml: String): List<FeedItem> {

        parser.setInput(xml.byteInputStream(), null)

        val items = mutableListOf<FeedItem>()

        while (parser.eventType != XmlPullParser.END_DOCUMENT) {
            if (parser.eventType  == XmlPullParser.START_TAG && parser.name == "item") {
                val item = readFeedItem(parser)
                items.add(item)
            }
            parser.next()
        }

        return items
    }

    private fun readFeedItem(parser: XmlPullParser): FeedItem {
        var title = ""
        var link = ""
        var pubDate = ""
        var image = ""

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType == XmlPullParser.START_TAG && parser.name == "title") {
                title = readText(parser)

            } else if(parser.eventType == XmlPullParser.START_TAG && parser.name == "link") {
                link = readText(parser)

            } else if(parser.eventType == XmlPullParser.START_TAG && parser.name == "pubDate") {
                pubDate = readText(parser)

            } else if(parser.eventType == XmlPullParser.START_TAG && parser.name == "cover_image") {
                image = readText(parser)

            } else if(parser.eventType == XmlPullParser.START_TAG) {
                skipTag(parser)
            }
        }

        return FeedItem(title, link, pubDate, image)
    }

    private fun readText(parser: XmlPullParser) : String {
        var text = ""
        while (parser.next() != XmlPullParser.END_TAG) {
            if(parser.eventType == XmlPullParser.TEXT) {
                text = parser.text
            }
        }
        return text
    }

    @Suppress("ControlFlowWithEmptyBody")
    private fun skipTag(parser: XmlPullParser)  {
        while (parser.next() != XmlPullParser.END_TAG) {
        }
    }
}