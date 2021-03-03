<#import "parts/common.ftlh" as c>

<@c.page>
<span><a href="/v1/public/characters">Characters</a></span>
<div>

     <#if filename??>
         <img src="/img/${filename}" alt="Avatar" height="500", width="400">
     </#if>

    <div class="container">
        <h4 href="/v1/public/characters/${comics.name}/comics}"><b>${name}</b></h4>
        <p>${description}</p>
    </div>
</div>
</@c.page>

