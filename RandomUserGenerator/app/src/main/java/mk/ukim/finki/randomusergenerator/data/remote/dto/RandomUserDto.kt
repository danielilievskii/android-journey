package mk.ukim.finki.randomusergenerator.data.remote.dto

data class RandomUserDto(
  val email: String,
  val picture: PictureDto
) {
}

data class PictureDto(
  val large: String
)