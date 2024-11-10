using System;
using System.Collections.Generic;
using System.Drawing.Printing;
using System.Drawing;
using System.Linq;
using System.Reflection.Emit;
using System.Runtime.Remoting.Contexts;
using System.Security.Policy;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using static System.Net.Mime.MediaTypeNames;

namespace AmorYPazBackend
{
    public partial class PerfilUsuario : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void btnSave_Click(object sender, EventArgs e)
        {
            string currentPass = currentPassword.Text;
            string newPass = newPassword.Text;
            string confirmPass = confirmPassword.Text;

            if (newPass == confirmPass)
            {
                // Logic to change password (e.g., update in database)
                // Show success message or redirect
            }
            else
            {
                // Show error message for mismatched passwords
            }
        }
    }
}