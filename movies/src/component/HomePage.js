import React, {Component} from 'react'
import PropTypes from 'prop-types';
import axios from 'axios';
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import Fab from '@material-ui/core/Fab';
import DeleteIcon from '@material-ui/icons/Delete';
import EditIcon from '@material-ui/icons/Edit';
import Grid from '@material-ui/core/Grid';
import { withStyles } from '@material-ui/core/styles';
import Typography from '@material-ui/core/Typography';

const styles =  {
    card: {
      width: 345,
    },
    media: {
      height: 140,
      width: 345,
  
    },
    fab: {
        marginRight: '5%',
        margin: 0,
    }
 


  };
class HomePage extends Component{
    state = {
        movies: []
    }
    componentDidMount(){
        axios.get("http://localhost:8080/films.api/films/getAll")
        .then(response =>
        this.setState({
            movies: response.data.slice()
        }))
        
    } 
    render(){
        const {classes}=this.props;
        const{movies} =this.state;
       
                return(
                    <div className="container">
                    <h1 className="center">Movies Library</h1>
                <Grid container spacing={24}>
                { movies? movies.map (movie => 
                <Grid key={movie.id}  item xs={3} >
                <Grid container justify='center'>

                <Card className={classes.card}>
                     <CardActionArea>
                 
                            <CardContent>
                            <Typography gutterBottom variant="headline" component="h2">
                                {movie.title}
                            </Typography> 
                            <h3>
                                Director: {movie.director}
                            </h3>
                            <h3>
                                Type: {movie.type}
                            </h3>
                            <h3>
                                Date: {movie.releaseDate}
                            </h3>
                            </CardContent>
                     </CardActionArea>
                 <CardActions>
                
                 <Fab color="secondary" aria-label="Edit" className={classes.fab}>
                    <EditIcon />
                </Fab>
                <Fab  aria-label="Delete" className={classes.fab}>
                    <DeleteIcon />
                </Fab>
                 </CardActions>
               </Card>
               </Grid>
               </Grid>): <div className="center"> No movie </div>}
                </Grid> 
                </div>  
                )
            
        
            
        
      
        
     }
}
HomePage.propTypes = {
    classes: PropTypes.object.isRequired, 
}
export default withStyles(styles)(HomePage);