<#import "parts/common.ftlh" as c>

<@c.page>
<div>

     <#if filename??>
         <img src="/img/${filename}" alt="Avatar" height="500", width="400">
     </#if>

    <div class="container">
        <h4><b>${name}</b></h4>
        <p>${description}</p>
    </div>
</div>
</@c.page>

