
async function calculateLove() {
    const firstName = document.getElementById("firstName").value.trim();
    const secondName = document.getElementById("secondName").value.trim();
    const result = document.getElementById("result");

    if (firstName === "" || secondName === "") {
        result.textContent = "Please enter both names!";
        return;
    }

    result.textContent = "Calculating... ❤️";

    try {
        const params = new URLSearchParams({
            firstName: firstName,
            secondName: secondName
        });

        const response = await fetch(
            "/api/love/calculate?" + params.toString(),
            { method: "POST" }
        );

        if (!response.ok) {
            const message = await response.text();
            throw new Error(message || "API request failed");
        }

        const data = await response.json();

        result.textContent =
            data.firstName + " ❤️ " +
            data.secondName + " = " +
            data.percentage + "% Love!";

    } catch (error) {
        result.textContent =
            "Error: " + error.message;
        console.error(error);
    }
}
