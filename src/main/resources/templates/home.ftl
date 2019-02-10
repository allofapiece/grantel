<#import "parts/common.ftl" as c>

<@c.page>

Hello

 <form action="/logout" method="post">
     <input type="submit" value="Sign Out"/>
     <input type="hidden" value="${_csrf.token}" name="_csrf">
 </form>
    <span><a href="/user">User list</a> </span>
<a href="/message">Messages</a>

</@c.page>