<#import "parts/common.ftlh" as c>
<#import "parts/pager.ftlh" as p>


<@c.page>
 <span><a href="/v1/public/comics">Comics</a></span>

    <div class="card-columns">
        <#list page as character>
            <div class="card">
               <img src="/img/${character.image}" height="300", width="200">
               <div class="container">
                   <h4 ><b><a href="/v1/public/characters/${character.name}/comics">${character.name}</a></b></h4>
                   <h3><b>${character.tag}</b></h3>
                   <p class="card-text">${character.description}</p>
               </div>
            </div>
        <#else>
            No message
        </#list>
    </div>
</@c.page>