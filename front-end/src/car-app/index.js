import React, { Component } from "react";
import CarList from './components/car-list';

class CarApp extends Component {
    render() {
        return (
            <div className="App">
                <header className="App-header">
                    <h1 className="App-title">Car List</h1>                    
                </header>
                <CarList />
            </div>
        )
    }
}

export default CarApp;