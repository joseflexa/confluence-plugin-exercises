package br.rnp.confluence.plugins.ao;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.transaction.TransactionCallback;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import static com.google.common.base.Preconditions.*;

@Scanned
public class BirthdayServlet extends HttpServlet {
    @ComponentImport
    private final ActiveObjects ao;

    @Inject
    public BirthdayServlet(ActiveObjects ao)
    {
        this.ao = checkNotNull(ao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        final PrintWriter w = res.getWriter();
        w.write("<h1>Birthdays</h1>");

        // the form to post more TODOs
        w.write("<form method=\"post\">");
        w.write("<input type=\"text\" name=\"fullName\" size=\"25\"/>");
        w.write("<input type=\"text\" name=\"date\" size=\"25\"/>");
        w.write("  ");
        w.write("<input type=\"submit\" name=\"submit\" value=\"Add\"/>");
        w.write("</form>");
        w.write("<ol>");

        ao.executeInTransaction(new TransactionCallback<Void>() // (1)
        {
            @Override
            public Void doInTransaction()
            {
                for (Birthday birthday : ao.find(Birthday.class)) // (2)
                {
                    w.printf("<li>%s - %s</li>", birthday.getFullName(), birthday.getDate());
                }
                return null;
            }
        });

        w.write("</ol>");
        w.write("<script language='javascript'>document.forms[0].elements[0].focus();</script>");

        w.close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        final String fullName = req.getParameter("fullName");
        final String date = req.getParameter("date");

        ao.executeInTransaction(new TransactionCallback<Birthday>() // (1)
        {
            @Override
            public Birthday doInTransaction()
            {
                final Birthday birthday = ao.create(Birthday.class); // (2)
                birthday.setFullName(fullName); // (3)
                birthday.setDate(date);
                birthday.save(); // (4)
                return birthday;
            }
        });

        res.sendRedirect(req.getContextPath() + "/plugins/servlet/birthday/list");
    }

}
