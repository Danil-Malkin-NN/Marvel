<#import "parts/common.ftlh" as c>

<@c.page>
<span><a href="/v1/public/comics">Comics</a></span>
<div>

     <#if filename??>
         <img src="/img/${filename}" alt="Avatar" height="500", width="400">
     </#if>

    <div class="container">
        <h4 href="/v1/public/characters/${name}/comics}"><b>${name}</b></h4>
        <p>${description}</p>
        <div>
            <span><a href="/v1/public/comics/${name}/characters">This comic has these characters ...</a></span>
        </div>
        <span><a href="/v1/public/comics/addComic"> Add character </a></span>

    </div>
</div>
</@c.page>

