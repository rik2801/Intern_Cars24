import React, { Component } from 'react'
import CarServices from './services/CarServices'

export default class ListCarComponent extends Component {
    constructor(props){
        super(props)
        this.state = {
            car:[]

        }
        this.addCar=this.addCar.bind(this);
        this.editCar=this.editCar.bind(this);
        this.deleteCar=this.deleteCar.bind(this);
    }
    deleteCar(id){
        // rest api call
        CarServices.deleteCar(id).then ( res=>
            {
            this.setState({car: this.state.car.filter(car=> car.id !==id)});
            });

    }
    ViewCar(id){
        this.props.history.push(`/viewCar/${id}`);
    }
    editCar(id){
     this.props.history.push(`/addCar/${id}`);
    }
    componentDidMount(){
        CarServices.getCar().then((res) => {
           this.setState({car:res.data});
        });
    }
    addCar(){
        this.props.history.push('/add-car/addCar');
    }
    render() {
        return (
            <div>
                <h2 className="text-center">Car List</h2>
                <div className="row">
                    <button className="btn btn-primary"style ={{marginBottom:"15px"}} onClick={this.addCar}>Add Car</button>
                </div>
                <div className="row">
                    <table className="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Brand</th>
                                <th>Model</th>
                                <th>Variant</th>
                                <th>Registration year</th>
                                <th>Registration State</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                             {
                                 this.state.car.map(
                                     car =>
                                     <tr key={car.id}>
                                         <td>{car.brand}</td>
                                         <td>{car.model}</td>
                                         <td>{car.variant}</td>
                                         <td>{car.registartionYear}</td>
                                         <td>{car.registartionState}</td>
                                         <td>
                                             <button onClick = { () =>this.editCar(car.id)}  className="btn btn-info">Update</button>
                                             <button style ={{marginLeft:"10px"}}onClick = { () =>this.deleteCar(car.id)}  className="btn btn-danger">Delete</button>
                                             <button style ={{marginLeft:"10px"}}onClick = { () =>this.ViewCar(car.id)}  className="btn btn-info">View</button>
                                         
                                         </td>
                                        

                                     </tr>
                                 )
                             }
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }
}
