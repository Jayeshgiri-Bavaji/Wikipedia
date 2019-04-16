package com.jbavaji.wikipedia.model


/**
 * Created by Jayeshgiri Bavaji on 4/12/2019.
 * Synechron Technologies
 * jayeshgiri.bavaji@synechron.com
 */
object Urls {

    val BaseUrl = "https://en.wikipedia.org/w/api.php"

    fun getSearchUrl(term: String, skip: Int, take: Int): String {
        /* return BaseUrl + "?action=query" +
                 "&formatversion=2" +
                 "&generator=prefixsearch" +
                 "&term=$term" +
                 "&gpslimit=$take" +
                 "&gpsoffset=$skip" +
                 "&prop=pageimages|info" +
                 "piprop=thumbnail|url" +
                 "&pithumbsize=200" +
                 "&pilimit=$take" +
                 "&wbptterms=description" +
                 "&format=json" +
                 "&inprop=url"*/


        return BaseUrl + "?action=query" +
                "&formatversion=2" +
                "&generator=prefixsearch" +
                "&gpssearch=$term" +
                "&gpslimit=$take" +
                "&gpsnamespace=$skip" +
                "&prop=pageimages|info" +
                "&piprop=thumbnail|url" +
                "&pithumbsize=200" +
                "&pilimit=$take" +
                "&wbptterms=description" +
                "&format=json" +
                "&inprop=url"
    }

    fun getRandomUrl(take: Int): String {
        /* return BaseUrl + "?action=query" +
                 "&format=json" +
                 "&formatversion=2" +
                 "&generator=random" +
                 "&grnnamespace=0" +
                 "&prop=pageimages|info" +
                 "&pilimit=$take" +
                 "&inprop=url" +
                 "&pithumbsize=200"*/


        return BaseUrl + "?action=query" +
                "&formatversion=2" +
                "&generator=random" +
                "&grnlimit=$take" +
                "&grnnamespace=0" +
                "&prop=pageimages|info" +
                "&pithumbsize=200" +
                "&pilimit=$take" +

                "&format=json" +
                "&inprop=thumbnail|url"

    }
}