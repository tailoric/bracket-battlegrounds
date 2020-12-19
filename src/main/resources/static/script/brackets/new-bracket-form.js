(function(){
        let form = document.querySelector(".new-bracket-form");
        form.addEventListener("submit", async function(event){
            event.preventDefault();
            let data = new FormData(this)
            console.log(JSON.stringify(data))
            const response = await fetch("/brackets/new", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(Object.fromEntries(data))
            })
            console.log(response);
        });
    }
)();