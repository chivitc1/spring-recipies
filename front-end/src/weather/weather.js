import React from 'react';

const WEATHER_URL = 'http://api.openweathermap.org/data/2.5/weather?q=Hanoi&units=Metric&APIkey=ee03d4d7630ed0a33b513af89975ade5';
class Weather extends React.Component {
    constructor(props) {
        super(props);
        this.state = { temp: 0, desc: '', icon: '', loading: true };
    }

    handleSubmit = (event) => {
        event.preventDefault();
    }

    componentDidMount() {
        fetch(WEATHER_URL)
            .then(response => response.json())
            .then(result => {
                console.log(result);
                this.setState({
                    temp: result.main.temp,
                    desc: result.weather[0].description,
                    loc: result.name,
                    icon: result.weather[0].icon,
                    loading: false
                })
            })
            .catch(err => console.error(err));
    }

    render() {
        const imgSrc = `http://openweathermap.org/img/w/${this.state.icon}.png`;
        if (this.state.loading) {
            return <p>Loading</p>;
        }
        return (
            <div className="App">
                <p>Location: {this.state.loc}</p>
                <p>Temperature: {this.state.temp}</p>
                <p>Description: {this.state.desc}</p>
                <img src={imgSrc} alt="Weather icon" />
            </div>
        );
    }
}

export default Weather;
