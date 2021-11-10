import { Box } from '@chakra-ui/react';
import React, { useContext, useEffect, useMemo, useState } from 'react';
import { Switch, Route, Router } from 'react-router-dom';
import CodeBox from './components/CodeBox';
import Header from './components/Header';
import NotFound from './components/NotFound';
import Poll from './components/Poll';
import SignIn from './components/SignIn';
import { AuthContext } from './context/AuthContext';
import { AuthContextState } from './lib/types';
import { getLocalStorageItem } from './utils';

const App = (): JSX.Element => {
    const [authState, setAuthState] = useState<AuthContextState | null>(null);
    const value = useMemo(() => ({ authState, setAuthState }), [authState, setAuthState]);

    return (
        <AuthContext.Provider value={value}>
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
        </AuthContext.Provider>
    );
};

export default App;
