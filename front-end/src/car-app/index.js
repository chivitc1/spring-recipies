import React, { Component } from "react";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import Login from './components/login';

class CarApp extends Component {
    render() {
        return (
            <div className="App">
                <AppBar position="static" color="default">
                    <Toolbar>Car App Demo</Toolbar>                
                </AppBar>
                <Login />
            </div>
        )
    }
}

export default CarApp;