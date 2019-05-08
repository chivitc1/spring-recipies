import React, { Component } from "react";
import CarList from './components/car-list';
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";

class CarApp extends Component {
    render() {
        return (
            <div className="App">
                <AppBar position="static" color="default">
                    <Toolbar>Car List</Toolbar>                
                </AppBar>
                <CarList />
            </div>
        )
    }
}

export default CarApp;