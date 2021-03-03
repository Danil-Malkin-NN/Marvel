<#import "parts/common.ftlh" as c>

<@c.page>

 <span><a href="/v1/public/characters">Characters</a></span>

    <div class="card-columns">
       <#list page as comics>
           <div class="card">
                   <div class="container">
                   <h4><b>${comics.name}</b></h4>
                   <h3><b>${comics.description}</b></h3>
                   <p class="card-text">${comics.description}</p>
               </div>
           </div>
       <#else>
           No message
       </#list>
    </div>

</@c.page>