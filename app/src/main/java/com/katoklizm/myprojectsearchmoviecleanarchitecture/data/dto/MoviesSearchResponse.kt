package com.katoklizm.myprojectsearchmoviecleanarchitecture.data.dto

import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.Movie

class MoviesSearchResponse(val searchType: String,
                           val expression: String,
                           val results: List<MovieDto>) : Response()