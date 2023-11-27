package com.katoklizm.myprojectsearchmoviecleanarchitecture.data.dto

class NamesSearchResponse(val searchType: String,
                          val expression: String,
                          val results: List<PersonDto>) : Response()