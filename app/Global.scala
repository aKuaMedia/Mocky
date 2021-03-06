import play.api._
import play.api.mvc._

object Global extends GlobalSettings {

  override def onRouteRequest(request: RequestHeader): Option[Handler] = {
    super.onRouteRequest(request).orElse {
      // Authorize url with any data AFTER the version/id
      val RegexMock = """/([a-zA-Z0-9]+)/([a-z0-9]+).*""".r
      request.path match {
        case RegexMock(version, id) => Some(controllers.Application.get(id, version))
        case _ => None
      }
    }
  }

}
