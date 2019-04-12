package com.jbavaji.wikipedia.provider

import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Handler
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.fuel.httpGet
import com.google.gson.Gson
import com.jbavaji.wikipedia.model.Urls
import com.jbavaji.wikipedia.model.WikiResult


/**
 * Created by Jayeshgiri Bavaji on 4/12/2019.
 * Synechron Technologies
 * jayeshgiri.bavaji@synechron.com
 */
class ArticleDataProvider {

    init {
        FuelManager.instance.baseHeaders = mapOf("User-Agent" to "Pluralsight Wikipedia")
    }

    fun search(term: String, skip: Int, take: Int, responseHandler: (result: WikiResult) -> Unit?) {
        Urls.getSearchUrl(term, skip, take)
                .httpGet()
                .responseObject(WikipediaDeserialize()) { _, response, result ->
                        // do something with result
                    if(response.httpStatusCode == 200){
                        throw Exception("Unable to get Articles")
                    }

                    val(data , _) = result
                    responseHandler.invoke(data as WikiResult)
                }
    }

    fun getRandom( take: Int, responseHandler: (result: WikiResult) -> Unit?) {
        Urls.getRandomUrl(take)
                .httpGet()
                .responseObject(WikipediaDeserialize()) { request, response, result ->
                    // do something with result

                    if(response.httpStatusCode == 200){
                        throw Exception("Unable to get Articles")
                    }

                    val(data , error) = result
                    responseHandler.invoke(data as WikiResult)
                }
    }

    class WikipediaDeserialize : ResponseDeserializable<WikiResult> {

        override fun deserialize(reader: java.io.Reader): WikiResult? {
            return Gson().fromJson(reader, WikiResult::class.java)
        }
    }
}