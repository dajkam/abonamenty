package com.filip.machaj.demo.model.user.security

import com.filip.machaj.demo.model.user.Role
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.security.web.savedrequest.HttpSessionRequestCache
import org.springframework.security.web.savedrequest.RequestCache
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.security.core.authority.AuthorityUtils



@Component
class WebSecurityAuthSuccessHandler: SimpleUrlAuthenticationSuccessHandler() {
    var requestCache = HttpSessionRequestCache()

    override fun onAuthenticationSuccess(request: HttpServletRequest?, response: HttpServletResponse?, authentication: Authentication?) {

        val roles = AuthorityUtils.authorityListToSet(authentication?.getAuthorities()) /// to pobiera prawdopodobnie role dostępne w projekcie => zrozumieć co to tak naprawde robi

        if (roles.contains(Role.ADMIN.poziom) || roles.contains(Role.STRAZNIK.poziom)) {
            response?.sendRedirect("/index.html")
        } else {
            response?.sendRedirect("/uliczny.html")
        }

    }

}

// old  onAuthenticationSuccess
/*   val saveRequest = requestCache.getRequest(request,response)
        if (saveRequest == null){
            clearAuthenticationAttributes(request)
            return
        }
        val parameter = request?.getParameter(targetUrlParameter)
        val ok = isAlwaysUseDefaultTargetUrl || targetUrlParameter != null// tu był targetUrlParameter oryginalnie
                && StringUtils.hasText(parameter)
        if(ok){
            requestCache.removeRequest(request,response)
            clearAuthenticationAttributes(request)
            response?.sendRedirect("index.html")
            return
        }
        clearAuthenticationAttributes(request)*/
