const selectEl = document.getElementById('select');
const registrationImage = document.getElementById('planet-img');

selectEl.addEventListener('change', function() {
    switch(true) {
        case this.value === 'mercury' : registrationImage.src = "img/merkury.svg";
        break;
        case this.value === 'uranus' : registrationImage.src = "img/Uran.svg";
        break;
        case this.value === 'venus' : registrationImage.src = "img/venera.png";
        break;
        case this.value === 'earth' : registrationImage.src = "img/zemlya.png";
        break;
        case this.value === 'mars' : registrationImage.src = "img/mars.png";
        break;
        case this.value === 'neptune' : registrationImage.src = "img/neptune.png";
        break;
}
})