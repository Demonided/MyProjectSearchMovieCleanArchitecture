package com.katoklizm.myprojectsearchmoviecleanarchitecture.data.converter

import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.dto.ActorResponse
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.dto.CastItemResponse
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.dto.DirectorsResponse
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.dto.MovieCastResponse
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.dto.OtherResponse
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.dto.WritersResponse
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.MovieCast
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.MovieCastPerson

class MovieCastConverter {

    fun convert(response: MovieCastResponse): MovieCast {
        return with(response) {
            MovieCast(
                imdbId = this.imDbId,
                fullTitle = this.fullTitle,
                directors = convertDirectors(this.directors),
                others = convertOthers(this.others),
                writers = convertWriters(this.writers),
                actors = convertActors(this.actors)
            )
        }
    }

    private fun convertDirectors(directorsResponse: DirectorsResponse): List<MovieCastPerson> {
        return directorsResponse.items.map { it.toMovieCastPerson() }
    }

    private fun convertWriters(writersResponse: WritersResponse): List<MovieCastPerson> {
        return writersResponse.items.map { it.toMovieCastPerson() }
    }

    private fun convertOthers(othersResponses: List<OtherResponse>): List<MovieCastPerson> {
        return othersResponses.flatMap { otherResponse ->
            otherResponse.items.map { it.toMovieCastPerson(jobPrefix = otherResponse.job) }
        }
    }

    private fun convertActors(actorsResponses: List<ActorResponse>): List<MovieCastPerson> {
        return actorsResponses.map { actor ->
            MovieCastPerson(
                id = actor.id,
                name = actor.name,
                description = actor.asCharacter,
                image = actor.image,
            )
        }
    }

    private fun CastItemResponse.toMovieCastPerson(jobPrefix: String = ""): MovieCastPerson {
        return MovieCastPerson(
            id = this.id,
            name = this.name,
            description = description,
             image = null
        )
    }

}