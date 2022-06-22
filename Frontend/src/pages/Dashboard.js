import React, { useEffect, useState } from 'react'
import { Box, Divider, Tab, Tabs, Grid, Alert, AlertTitle, IconButton, ListItem, List, ListItemAvatar, Avatar, ListItemText } from '@mui/material'
import PostCard from '../components/PostCard';
import { a11yProps, TabPanel } from '../components/MuiTabPanel';
import { getFollowingPostsRequest } from '../api/controllers/post-controller';
import { getMyFollows } from '../api/controllers/customer-controller';
import { RemoveCircleRounded } from '@mui/icons-material';
import { useSnackbar } from 'notistack';
import { updUnFollowSellerRequest } from '../api/controllers/seller-controller';
import { BASE_URL } from '../api/ApiProvider';


const Dashboard = () => {
    const { enqueueSnackbar } = useSnackbar();

    const user = JSON.parse(localStorage.getItem("user"));

    const [tabValue, setTabValue] = useState(0);

    const handleChange = (event, newValue) => {
        setTabValue(newValue);
    };
    const [posts, setPosts] = useState([])
    const [myFollows, setMyFollows] = useState([])



    const fetchMyFollows = async () => {
        try {
            let res = await getMyFollows();
            if (res) {
                setMyFollows(res.data)
            }
        } catch (error) {
            if (error.response.status === 401) {
                enqueueSnackbar('Lütfen giriş yapın!', { variant: 'error' });
            } else if (error.response.status === 403) {
                enqueueSnackbar('Bu işlem için yetkiniz bulunmuyor!', { variant: 'error' });
            } else {
                enqueueSnackbar('Bir hata meydana geldi. Daha sonra tekrar deneyin!', { variant: 'error' });
            }
        }
    }

    const fetchPosts = async () => {
        try {
            let res = await getFollowingPostsRequest();
            if (res) {
                setPosts(res.data)
            }
        } catch (error) {
            if (error.response.status === 401) {
                enqueueSnackbar('Lütfen giriş yapın!', { variant: 'error' });
            } else if (error.response.status === 403) {
                enqueueSnackbar('Bu işlem için yetkiniz bulunmuyor!', { variant: 'error' });
            } else {
                enqueueSnackbar('Bir hata meydana geldi. Daha sonra tekrar deneyin!', { variant: 'error' });
            }
        }
    }

    const unFollow = async (id) => {
        try {
            let res = await updUnFollowSellerRequest(id);

            if (res) {
                enqueueSnackbar('Mağazayı takibi bıraktın :(', { variant: 'error' });
                fetchMyFollows()
            }
        } catch (error) {
            console.log(error);
        }
    }


    useEffect(() => {

        fetchMyFollows()
        fetchPosts()

    }, [])


    const Discover = () => {

        return (
            <Box sx={{ display: 'flex', flexDirection: 'column', gap: '1.5rem' }}>
                {
                    posts.length > 0 ?
                        posts.map((val, i) =>

                            <PostCard key={i} postData={val} />

                        ) :
                        <Alert severity="warning">
                            <AlertTitle>Henüz paylaşım yapılmamış :(</AlertTitle>
                            Burada içerik görmek istersen daha fazla mağazayı <strong>takip etmelisin!</strong>
                        </Alert>
                }
            </Box>
        )
    }

    return (

        <Box sx={{ textAlign: 'left', padding: '2rem', minHeight: '60vh' }}>
            <h2 className='title'>hoşgeldin, {
                user?.role === "ROLE_CUSTOMER" ?
                    user?.name :
                    user?.role === "ROLE_SELLER" ?
                        user?.storeName : "Kullanıcı"
            }
            </h2>
            <p className='subTitle'>Pazarda sana ait olayları görüntüleyebilir ve aşağıdan pazar ahalisinin paylaştıklarını görebilirsin...</p>
            <Divider sx={{ my: 3 }} />
            <Grid container spacing={3} >
                <Grid item xs={12} sm={4} md={3}>
                    <Tabs
                        orientation="vertical"
                        value={tabValue}
                        onChange={handleChange}
                        sx={{ borderRight: 1, borderColor: 'divider' }}
                    >
                        <Tab sx={{ alignItems: 'flex-start' }} label={<p style={{ textAlign: 'left' }}>PAYLAŞIMLAR</p>} {...a11yProps(0)} />
                        <Tab sx={{ alignItems: 'flex-start' }} label={
                            <p style={{ textAlign: 'left' }}>TAKİP ETTİKLERİM</p>
                        } {...a11yProps(3)} />
                    </Tabs>
                </Grid>
                <Grid item xs={12} sm={8} md={9}>
                    <TabPanel value={tabValue} index={0}>
                        <Discover />
                    </TabPanel>
                    <TabPanel value={tabValue} index={1}>
                        <List>
                            {
                                myFollows.map((val) =>
                                    <ListItem
                                        key={val?.id}
                                        secondaryAction={
                                            <IconButton onClick={() => unFollow(val?.id)} edge="end" aria-label="delete">
                                                <RemoveCircleRounded />
                                            </IconButton>
                                        }
                                    >
                                        <ListItemAvatar>
                                            <Avatar src={`${BASE_URL}/api/seller/${val?.id}/profilePhoto`} />
                                        </ListItemAvatar>
                                        <ListItemText
                                            primary={val?.storeName}
                                        />
                                    </ListItem>
                                )
                            }
                        </List>
                    </TabPanel>
                </Grid>
            </Grid>
        </Box>

    )
}
export default Dashboard;