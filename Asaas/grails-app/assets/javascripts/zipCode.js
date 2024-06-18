const zipCode = document.getElementById('zipCode')

zipCode.addEventListener("atlas-input-change", (e) => {
    let zipCodeOnlyNumeric = e.target.value.replace("-","")
    if(zipCodeOnlyNumeric.length == 8){
        getCepInfo(zipCodeOnlyNumeric).then(resp => {
            document.getElementById("province").value = resp.bairro ?? ''
            document.getElementById("city").value = resp.localidade ?? ''
            document.getElementById("street").value = resp.logradouro ?? ''
            document.getElementById("state").value = resp.uf ?? ''
        })
    }
})
async function getCepInfo(zipCode){
    const response = await fetch(`https://viacep.com.br/ws/${zipCode}/json/`)
    const convertedCep = await response.json()
    return convertedCep
}