package mk.ukim.finki.randomusergenerator.data.remote

import mk.ukim.finki.randomusergenerator.data.remote.dto.RandomUserDto

interface IRemoteRandomUserSource {
    suspend fun generateUser(): RandomUserDto
}