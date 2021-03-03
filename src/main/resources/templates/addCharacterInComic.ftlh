<#import "parts/common.ftlh" as c>


<@c.page>
    <div>
        <span><a href="/v1/public/character"></a>character</span>
    </div>
    <div>
        <form method="post" action="/v1/public/characters/addCharacterInComics" enctype="multipart/form-data">
            <input type="text" name="name" placeholder="Имя героя" />
            <input type="text" name="comics" placeholder="Название комикса">
            <button type="submit">Добавить</button>
        </form>
    </div>
</@c.page>