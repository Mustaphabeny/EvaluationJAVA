import React from 'react';
import { withStyles, AppBar, Toolbar, Button} from '@material-ui/core';
import TextField from '@material-ui/core/TextField';

const style = theme => ({
    textField: {
        marginLeft: theme.spacing.unit,
        marginRight: theme.spacing.unit,
        width: 200,
        
      },
      container: {
        flexDirection: 'row',
        flexWrap: 'nowrap',
        justifyContent: 'center',
        width: 300,
        
      },
    titleButton: {
    color: 'white',
    fontSize: '100%',
    fontFamily: 'Comic Sans MS',
    borderRadius: '22%',
   
    

  },



});

class HeaderBar extends React.Component {

  render() {
    const { classes } = this.props;

    return (
        <AppBar position="static">
          <Toolbar>
              <Button className={classes.titleButton} justify= 'center'>
                Add Movie
               </Button>
               <div className={classes.container}>
               <TextField
                 label="Search movie"
                  className={classes.textField}
                  type="text"
                  margin="auto"
                  
                />
           </div>
          </Toolbar>
        </AppBar>
    );
  }
}
export default withStyles(style)(HeaderBar);