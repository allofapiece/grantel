<#import "parts/common.ftl" as c>

<@c.page>

<span><a href="/">Home</a> </span>

<div>Hello, User</div>

<form method="post" action="/message" enctype="multipart/form-data">
    <input type="text" name="text" placeholder="Enter text"/>
    <input type="file" name="file">
    <input type="hidden" name="id" value="<#if message??>${message.id}</#if>">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit">Submit</button>
</form>

<div class="messages">
    <#list messages as message>
        <div class="message">
            <b>${message.id}</b>
            <span>${message.text}</span>
            <i>${message.creation}</i>
            <strong><#if message.author??>${message.author.username}<#else>Unknown author</#if></strong>
            <div>
                <#if message.filename??>
                    <img src="/img/${message.filename}"/>
                </#if>
            </div>
        </div>
    </#list>
</div>

</@c.page>