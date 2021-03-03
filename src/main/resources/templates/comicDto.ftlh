<#import "parts/common.ftlh" as c>

<@c.page>

 <span><a href="/v1/public/characters">Characters</a></span>
 <span><a href="/v1/public/comics">Comics</a></span>


    <div class="card-columns">
       <#list page as comics>
           <div class="card">
              <img src="/img/${comics.image}" height="300", width="200">
                   <div class="container">
                   <h4><b>${comics.name}</b></h4>
                   <h3><b>${comics.tag}</b></h3>
                   <p class="card-text">${comics.description}</p>
               </div>
           </div>
       <#else>
           No message
       </#list>
    </div>

</@c.page>