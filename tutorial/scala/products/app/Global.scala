import org.squeryl.SessionFactory
import play.api.GlobalSettings
import play.api.Application
import org.squeryl.adapters.PostgreSqlAdapter
import play.api.db.DB
import org.squeryl.Session

object Global extends GlobalSettings {
  override def onStart(app: Application) {
    SessionFactory.concreteFactory = Some(() =>
      Session.create(DB.getConnection()(app), new PostgreSqlAdapter))
  }
}