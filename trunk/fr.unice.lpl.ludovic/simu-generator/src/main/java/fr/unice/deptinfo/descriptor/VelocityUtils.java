package fr.unice.deptinfo.descriptor;
import java.io.StringWriter;
import java.io.Writer;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class VelocityUtils {

 private static Logger log = Logger.getLogger(VelocityUtils.class);

 /**
  * private constructor
  */
 private VelocityUtils() {
 }

 /**
  * apply Velocity template
  *
  * @param templateFile
  * @param Context
  * @return
  * @throws Exception
  */
 public static String applyTemplate(String templateFile,
   VelocityContext context) throws Exception {
  try {
   Velocity.init();

   Template template = Velocity.getTemplate(templateFile);

   Writer writer = new StringWriter();
   template.merge(context, writer);
   return writer.toString();

  } catch (Exception e) {
   log.error(e);
   throw e;
  }
 }
}