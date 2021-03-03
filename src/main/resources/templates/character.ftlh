<#import "parts/common.ftlh" as c>

<@c.page>
<span><a href="/v1/public/characters">Characters</a></span>
<div>

     <#if filename??>
         <img src="/img/${filename}" alt="Avatar" height="500", width="400">
     </#if>
    <div class="container">
        <h4><b>${name}</b></h4>
        <p>${description}</p>
        <div>
        <span><a href="/v1/public/characters/${name}/comics">This comic has these characters ...</a></span>
        </div>
        <span><a href="/v1/public/characters/addComic"> Add Comics </a></span>

    </div>
</div>
</@c.page>

