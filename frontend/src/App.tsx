import { Box } from '@chakra-ui/react';
import React, { useContext, useEffect, useMemo, useState } from 'react';
import { Switch, Route, Router } from 'react-router-dom';
import CodeBox from './components/CodeBox';
import Header from './components/Header';
import NotFound from './components/NotFound';
import Poll from './components/Poll';
import SignIn from './components/SignIn';
import { AuthContext, AuthProvider } from './context/AuthContext';

const App = (): JSX.Element => {
    return (
        <AuthProvider>
            <Box h="100vh" bgColor="red.100">
                <Header />
                <Switch>
                    <Route path="/signin">
                        <SignIn />
                    </Route>
                    <Route exact path="/polls">
                        <Poll />
                    </Route>
                    <Route path="/polls/:id">
                        <Poll />
                    </Route>
                    <Route exact path="/">
                        <CodeBox />
                    </Route>
                    <Route>
                        <NotFound />
                    </Route>
                </Switch>
            </Box>
        </AuthProvider>
    );
};

export default App;
